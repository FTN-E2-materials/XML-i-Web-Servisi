package main

type RequestPost struct {
	Title string   `json:"title"`
	Text  string   `json:"text"`
	Tags  []string `json:"tags"`
}

type ResponseId struct {
	Id int `json:"id"`
}
