#!/usr/bin/env bash

set -euo pipefail

read -r -p "Enter a directory name: " directory_name

mkdir -p "$directory_name"

process_file="$directory_name/running_processes.txt"
touch "$process_file"

current_date=$(date)
host_name=$(hostname)
user_name=$(whoami)
disk_usage=$(df -h)

echo "Current Date: $current_date"
echo "Hostname: $host_name"
echo "Username: $user_name"
echo
echo "Disk Usage:"
printf '%s\n' "$disk_usage"
echo
echo "Running Processes:"
ps aux

ps aux > "$process_file"
echo
echo "Running processes saved to: $process_file"
