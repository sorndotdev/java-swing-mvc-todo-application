#!/bin/bash

SRC_DIR="."
OUT_DIR="out"

mkdir -p "$OUT_DIR"

javac -d "$OUT_DIR" $(find "$SRC_DIR" -name "*.java")
java -cp "$OUT_DIR" todo.TodoApp
