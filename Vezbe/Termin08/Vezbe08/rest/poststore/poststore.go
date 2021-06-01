package poststore

import (
	"context"
	"fmt"
	tracer "github.com/milossimic/rest/tracer"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
	"os"
)

type PostStore struct {
	db *gorm.DB
}

func New() (*PostStore, error) {
	ts := &PostStore{}

	host := os.Getenv("DBHOST")
	user := os.Getenv("USER")
	password := os.Getenv("PASSWORD")
	dbname := os.Getenv("DBNAME")
	dbport := os.Getenv("DBPORT")

	dsn := fmt.Sprintf("host=%s user=%s password=%s dbname=%s port=%s sslmode=disable TimeZone=Asia/Shanghai", host, user, password, dbname, dbport)
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		return nil, err
	}
	ts.db = db
	ts.db.AutoMigrate(&Post{}, &Tag{})

	return ts, nil
}

func (ts *PostStore) CreatePost(ctx context.Context, title string, text string, tags []string) int {
	span := tracer.StartSpanFromContext(ctx, "CreatePost")
	defer span.Finish()

	post := Post{
		Text:  text,
		Title: title}

	newTags := []Tag{}
	for _, tag := range tags {
		newTags = append(newTags, Tag{Name: tag})
	}
	post.Tags = newTags

	ts.db.Create(&post)

	return int(post.ID)
}

func (ts *PostStore) GetPost(ctx context.Context, id int) (Post, error) {
	span := tracer.StartSpanFromContext(ctx, "GetPost")
	defer span.Finish()

	var post Post
	result := ts.db.Preload("Tags").Find(&post, id)

	if result.RowsAffected > 0 {
		return post, nil
	}

	return Post{}, fmt.Errorf("post with id=%d not found", id)
}

func (ts *PostStore) DeletePost(ctx context.Context, id int) error {
	span := tracer.StartSpanFromContext(ctx, "DeletePost")
	defer span.Finish()

	result := ts.db.Delete(&Post{}, id)
	if result.RowsAffected > 0 {
		return nil
	}

	return fmt.Errorf("post with id=%d not found", id)
}

// GetAllTasks returns all the tasks in the store, in arbitrary order.
func (ts *PostStore) GetAllPosts(ctx context.Context) []Post {
	span := tracer.StartSpanFromContext(ctx, "GetAllPosts")
	defer span.Finish()

	var posts []Post
	ts.db.Preload("Tags").Find(&posts)

	return posts
}

func (ts *PostStore) Close() error {
	db, err := ts.db.DB()
	if err != nil {
		return err
	}

	db.Close()
	return nil
}
