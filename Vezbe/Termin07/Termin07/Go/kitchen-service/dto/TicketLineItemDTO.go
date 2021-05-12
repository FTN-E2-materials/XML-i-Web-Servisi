package dto

type TicketLineItemDTO struct {
	MenuItemId   string `json:"item_id"`
	MenuItemName string `json:"item_name"`
	Quantity     int    `json:"quantity"`
}
