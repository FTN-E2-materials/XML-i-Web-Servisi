package model

type TicketState int

const (
	PENDING TicketState = iota
	ACCEPTED
	REJECTED
)
