#!/bin/sh
# 程序的根目录
basedir=/usr/local/judge

PID=$(cat $basedir/judge.pid)
kill "$PID"
