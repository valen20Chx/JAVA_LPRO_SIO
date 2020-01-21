clientPath="./src/client/"
drawPath="${clientPath}/drawing/"
netPath="${clientPath}/network/"

javaFiles="${clientPath}/*.java ${drawPath}/*.java ${netPath}/*.java"

javac ${javaFiles} -d bin/client