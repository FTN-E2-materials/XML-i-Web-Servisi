package main

import (
	"fmt"
	"kitchen-service/handler"
	"kitchen-service/model"
	"kitchen-service/repository"
	"kitchen-service/service"
	"log"
	"net/http"
	"os"

	"github.com/gorilla/mux"
	"gorm.io/driver/sqlite"
	"gorm.io/gorm"
)

func initDB() *gorm.DB {
	database, err := gorm.Open(sqlite.Open("kitchen.db"), &gorm.Config{})
	if err != nil {
		log.Fatal(err)
	}
	database.AutoMigrate(&model.Restaurant{})
	database.AutoMigrate(&model.MenuItem{})
	database.AutoMigrate(&model.Ticket{})
	database.AutoMigrate(&model.TicketLineItem{})

	/*Loading test data*/

	restaurant := model.Restaurant{Name: "Tamo Daleko"}
	menuItems := []model.MenuItem{
		{Name: "gulas"}, {Name: "palacinke"}, {Name: "riblja corba"},
	}
	restaurant.MenuItems = menuItems
	database.Create(&restaurant)

	ticketItems := []model.TicketLineItem{
		{MenuItemID: menuItems[0].ID, Quantity: 3}, {MenuItemID: menuItems[1].ID, Quantity: 2},
	}
	ticket := model.Ticket{
		TicketState: model.PENDING, Items: ticketItems, RestaurantID: restaurant.ID,
	}
	database.Create(&ticket)
	return database
}

func initRestaurantRepo(database *gorm.DB) *repository.RestaurantRepository {
	return &repository.RestaurantRepository{Database: database}
}

func initMenuItemRepository(database *gorm.DB) *repository.MenuItemRepository {
	return &repository.MenuItemRepository{Database: database}
}

func initTicketRepository(database *gorm.DB) *repository.TicketRepository {
	return &repository.TicketRepository{Database: database}
}

func initServices(menuItemRepo *repository.MenuItemRepository, restaurantRepo *repository.RestaurantRepository, ticketRepo *repository.TicketRepository) *service.TicketService {
	return &service.TicketService{MenuItemRepo: menuItemRepo, RestaurantRepo: restaurantRepo, TicketRepo: ticketRepo}
}

func initHandler(service *service.TicketService) *handler.KitchenHandler {
	return &handler.KitchenHandler{Service: service}
}

func handleFunc(handler *handler.KitchenHandler) {
	router := mux.NewRouter().StrictSlash(true)

	router.HandleFunc("/", handler.Hello).Methods("GET")
	router.HandleFunc("/create/{restaurantId}/{orderId}", handler.Create).Methods("POST")
	router.HandleFunc("/verify/{restaurantId}", handler.Verify).Methods("POST")
	router.HandleFunc("/update/{ticketId}/{state}", handler.Update).Methods("PUT")

	fmt.Println("server running ")
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%s", os.Getenv("PORT")), router))
}

func main() {
	database := initDB()
	restaurantRepo := initRestaurantRepo(database)
	menuItemRepo := initMenuItemRepository(database)
	TicketRepository := initTicketRepository(database)
	service := initServices(menuItemRepo, restaurantRepo, TicketRepository)
	handler := initHandler(service)

	handleFunc(handler)
}
