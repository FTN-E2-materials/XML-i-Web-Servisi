package handler

import (
	"encoding/json"
	"fmt"
	"net"
	"net/http"

	"github.com/gorilla/mux"
	"github.com/veljkomaksimovic/order-service/model"
	"github.com/veljkomaksimovic/order-service/service"
)

type OrderHandler struct {
	Service *service.OrderService
}

func (handler *OrderHandler) Hello(w http.ResponseWriter, r *http.Request) {
	addrs, _ := net.InterfaceAddrs()
	for i, addr := range addrs {
		fmt.Printf("%d %v\n", i, addr)
	}
}

func (handler *OrderHandler) CreateOrder(w http.ResponseWriter, r *http.Request) {
	var order model.Order
	err := json.NewDecoder(r.Body).Decode(&order)
	if err != nil {
		fmt.Println(err)
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	err = handler.Service.CreateOrder(&order)
	if err != nil {
		fmt.Println(err)
		w.WriteHeader(http.StatusExpectationFailed)
		return
	}
	w.WriteHeader(http.StatusCreated)
	w.Header().Set("Content-Type", "application/json")
}

func (handler *OrderHandler) UpdateStatus(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	id := vars["orderId"]
	if id == "" {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	status := vars["status"]
	if status == "" {
		w.WriteHeader(http.StatusBadRequest)
		return
	}
	err := handler.Service.ChangeStatus(id, status)
	if err != nil {
		print(err)
		w.WriteHeader(http.StatusBadRequest)
	}
}
