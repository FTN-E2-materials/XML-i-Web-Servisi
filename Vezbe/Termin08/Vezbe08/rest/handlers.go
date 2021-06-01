package main

import (
	"context"
	"fmt"
	"github.com/gorilla/mux"
	tracer "github.com/milossimic/rest/tracer"
	"mime"
	"net/http"
	"strconv"
)

func (ts *postServer) createPostHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("cretePostHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling post create at %s\n", req.URL.Path)),
	)

	// Enforce a JSON Content-Type.
	contentType := req.Header.Get("Content-Type")
	mediatype, _, err := mime.ParseMediaType(contentType)
	if err != nil {
		tracer.LogError(span, err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	if mediatype != "application/json" {
		http.Error(w, "expect application/json Content-Type", http.StatusUnsupportedMediaType)
		return
	}

	ctx := tracer.ContextWithSpan(context.Background(), span)
	rt, err := decodeBody(ctx, req.Body)
	if err != nil {
		tracer.LogError(span, err)
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}

	id := ts.store.CreatePost(ctx, rt.Title, rt.Text, rt.Tags)
	renderJSON(ctx, w, ResponseId{Id: id})
}

func (ts *postServer) getAllPostsHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("getAllPostsHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling get all posts at %s\n", req.URL.Path)),
	)

	ctx := tracer.ContextWithSpan(context.Background(), span)
	allTasks := ts.store.GetAllPosts(ctx)
	renderJSON(ctx, w, allTasks)
}

func (ts *postServer) getPostHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("getPostHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling get all posts at %s\n", req.URL.Path)),
	)

	ctx := tracer.ContextWithSpan(context.Background(), span)
	id, _ := strconv.Atoi(mux.Vars(req)["id"])
	task, err := ts.store.GetPost(ctx, id)

	if err != nil {
		tracer.LogError(span, err)
		http.Error(w, err.Error(), http.StatusNotFound)
		return
	}

	renderJSON(ctx, w, task)
}

func (ts *postServer) deletePostHandler(w http.ResponseWriter, req *http.Request) {
	span := tracer.StartSpanFromRequest("deletePostHandler", ts.tracer, req)
	defer span.Finish()

	span.LogFields(
		tracer.LogString("handler", fmt.Sprintf("handling delete post at %s\n", req.URL.Path)),
	)

	ctx := tracer.ContextWithSpan(context.Background(), span)
	id, _ := strconv.Atoi(mux.Vars(req)["id"])
	err := ts.store.DeletePost(ctx, id)

	if err != nil {
		tracer.LogError(span, err)
		http.Error(w, err.Error(), http.StatusNotFound)
	}
}
