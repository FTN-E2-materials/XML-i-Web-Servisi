package main

import (
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/google/uuid"
	"github.com/gorilla/mux"
	"github.com/veljkomaksimovic/order-service/handler"
	"github.com/veljkomaksimovic/order-service/model"
	"github.com/veljkomaksimovic/order-service/repository"
	"github.com/veljkomaksimovic/order-service/service"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

func initDB() *gorm.DB {
	database, err := gorm.Open(sqlite.Open("orders.db"), &gorm.Config{})
	if err != nil {
		log.Fatal(err)
	}
	database.AutoMigrate(&model.Order{})
	database.AutoMigrate(&model.OrderLineItem{})

	/*Loading test data*/
	orders := []model.Order{
		{RestaurantID: uuid.New(), ConsumerID: uuid.New(), OrderStatus: model.PENDING, OrderItems: []model.OrderLineItem{
			{MenuItemId: uuid.New(), MenuItemName: "jaja", Quantity: 6},
			{MenuItemId: uuid.New(), MenuItemName: "palacinke", Quantity: 1},
		}},
		{RestaurantID: uuid.New(), ConsumerID: uuid.New(), OrderStatus: model.ACCEPTED, OrderItems: []model.OrderLineItem{
			{MenuItemId: uuid.New(), MenuItemName: "carbonara", Quantity: 2},
			{MenuItemId: uuid.New(), MenuItemName: "mleko", Quantity: 3},
		}},
	}
	for i := range orders {
		database.Create(&orders[i])
	}

	/*Primer eager i lazy ucitavanja entiteta koji imaju one-to-many asocijacije uz GORM object relation mapper
	//1' eager loading:
	var orderEagerLoading model.Order
	database.Preload(clause.Associations).First(&orderEagerLoading, "id = ?", orders[0].ID)
	fmt.Println("---------Eager Loading---------")
	fmt.Println(orderEagerLoading)
	//2' lazy loading:
	var orderLazyLoading model.Order
	database.First(&orderLazyLoading, "id = ?", orders[0].ID)
	fmt.Println("---------Lazy Loading---------")
	fmt.Println(orderLazyLoading)
	*/
	return database
}

func initRepo(database *gorm.DB) *repository.OrderRepository {
	return &repository.OrderRepository{Database: database}
}

func initServices(repo *repository.OrderRepository) *service.OrderService {
	return &service.OrderService{Repo: repo}
}

func initHandler(service *service.OrderService) *handler.OrderHandler {
	return &handler.OrderHandler{Service: service}
}
func handleFunc(handler *handler.OrderHandler) {
	router := mux.NewRouter().StrictSlash(true)

	router.HandleFunc("/", handler.Hello).Methods("GET")
	router.HandleFunc("/", handler.CreateOrder).Methods("POST")
	router.HandleFunc("/{orderId}/{status}", handler.UpdateStatus).Methods("GET")

	fmt.Println("server running ")
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%s", os.Getenv("PORT")), router))
}

func main() {
	database := initDB()
	repo := initRepo(database)
	service := initServices(repo)
	handler := initHandler(service)
	handleFunc(handler)
}
