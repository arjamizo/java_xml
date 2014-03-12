# This must be run via /bin/bash shell, sao Git Bash works as well. 
ls *.xml -1 | sed 's#.*#<a href="\0">\0</a><br/>#' > listedxmls.html