clientPath="./src/client/"
drawPath="${clientPath}/drawing/"
# netPath="${drawPath}/network/"
netPath=""

javaFiles="${clientPath}/*.java ${drawPath}/*.java"

javac ${javaFiles} -d bin