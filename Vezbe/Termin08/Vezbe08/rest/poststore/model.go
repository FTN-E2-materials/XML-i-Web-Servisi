package poststore

type Post struct {
	ID    int    `json:"id"`
	Title string `json:"title"`
	Text  string `json:"text"`
	Tags  []Tag  `gorm:"polymorphic:Owner;" json:"tags"`
}

type Tag struct {
	ID        int    `json:"id"`
	Name      string `json:"name"`
	OwnerID   int
	OwnerType string
}
