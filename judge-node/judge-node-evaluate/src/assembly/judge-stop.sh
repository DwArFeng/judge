#!/bin/bash
# 程序的根目录
basedir=/usr/local/judge-evaluate

PID=$(cat $basedir/judge.pid)
kill "$PID"
