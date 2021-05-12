package service

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
	"os"

	"github.com/google/uuid"
	"github.com/veljkomaksimovic/order-service/model"
	"github.com/veljkomaksimovic/order-service/repository"
)

type OrderService struct {
	Repo *repository.OrderRepository
}

func (service *OrderService) CreateOrder(order *model.Order) error {
	err_cons := verify(os.Getenv("CONSUMER_SERVICE_DOMAIN"), os.Getenv("CONSUMER_SERVICE_PORT"), order.ConsumerID.String())
	//err_rest := verify(os.Getenv("KITCHEN_SERVICE_DOMAIN"), os.Getenv("KITCHEN_SERVICE_PORT"), order.RestaurantID.String())
	var err_rest error = nil
	if err_cons != nil || err_rest != nil {
		if err_cons != nil {
			return err_cons
		}
		return err_rest
	}
	order.OrderStatus = model.PENDING
	err := service.Repo.CreateOrder(order)
	if err != nil {
		return err
	}
	req_url := fmt.Sprintf("http://%s:%s/create/%s/%s", os.Getenv("KITCHEN_SERVICE_DOMAIN"), os.Getenv("KITCHEN_SERVICE_PORT"), order.RestaurantID, order.ID)
	json_orders, _ := json.Marshal(order)
	fmt.Printf("Sending POST req to url %s\nJson being sent:\n", req_url)
	fmt.Println(string(json_orders))
	resp, err := http.Post(req_url, "application/json", bytes.NewBuffer(json_orders))
	if err != nil || resp.StatusCode == 404 {
		print("Failed creating ticket in kitchen-service")
		return fmt.Errorf("failed creating ticket in kitchen-service")
	}
	return nil
}

func verify(domain, port, id string) error {
	url := fmt.Sprintf("http://%s:%s/verify/%s", domain, port, id)
	fmt.Printf("Verifying for url %s\n", url)
	resp, err := http.Get(url)
	if err != nil || resp.StatusCode == 404 {
		fmt.Printf("Verification failed for domain %s and id %s\n", domain, id)
		return fmt.Errorf(fmt.Sprintf("verification failed for domain %s and id %s", domain, id))
	}
	return nil
}
func (service *OrderService) ChangeStatus(orderId string, status string) error {
	id, err := uuid.Parse(orderId)
	if err != nil {
		print(err)
		return err
	}
	var orderStatus int
	switch status {
	case "pending":
		orderStatus = model.PENDING
	case "accepted":
		orderStatus = model.ACCEPTED
	case "rejected":
		orderStatus = model.REJECTED
	}
	service.Repo.UpdateOrder(id, orderStatus)
	return nil
}
