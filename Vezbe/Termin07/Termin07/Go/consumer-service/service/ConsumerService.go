package service

import (
	"github.com/google/uuid"
	"github.com/veljkomaksimovic/nginx-example/model"
	"github.com/veljkomaksimovic/nginx-example/repository"
)

type ConsumerService struct {
	Repo *repository.ConsumerRepository
}

func (service *ConsumerService) CreateConsumer(consumer *model.Consumer) error {
	service.Repo.CreateConsumer(consumer)
	return nil
}

func (service *ConsumerService) UserExists(consumerId string) (bool, error) {
	id, err := uuid.Parse(consumerId)
	if err != nil {
		print(err)
		return false, err
	}
	exists := service.Repo.ConsumerExists(id)
	return exists, nil
}
