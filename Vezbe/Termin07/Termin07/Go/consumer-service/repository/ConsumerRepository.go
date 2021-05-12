package repository

import (
	"fmt"

	"github.com/google/uuid"
	"github.com/veljkomaksimovic/nginx-example/model"
	"gorm.io/gorm"
)

type ConsumerRepository struct {
	Database *gorm.DB
}

func (repo *ConsumerRepository) CreateConsumer(consumer *model.Consumer) error {
	result := repo.Database.Create(consumer)
	//TODO convert to logs
	fmt.Println(result.RowsAffected)
	return nil
}

func (repo *ConsumerRepository) ConsumerExists(consumerId uuid.UUID) bool {
	var count int64
	repo.Database.Where("id = ?", consumerId).Find(&model.Consumer{}).Count(&count)
	return count != 0
}
