#!/bin/bash

echo "Please enter your username."
read uname
# PWD is already a variable, all you need is $PWD
#date required is MMDDYY
#$(date) offers weekdaymonthDD Time (24 hour, down to the second) YYYY

echo "Welcome $uname! You are currently relaxing within $PWD. The current time is $(date)"
