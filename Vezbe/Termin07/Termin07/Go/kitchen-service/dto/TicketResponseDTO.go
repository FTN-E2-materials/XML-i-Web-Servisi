package dto

type TicketResponseDTO struct {
	RestaurantName  string              `json:"restaurantName"`
	TicketState     string              `json:"ticketState"`
	TicketLineItems []TicketLineItemDTO `json:"items"`
}
