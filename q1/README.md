# How I do that 
I use apache-cassandra-2.1.7, apache-spark-1.4.0-hadoop-1.0.4 version. The programming language is scala. 

# Data
The is download from http://www.ncdc.noaa.gov/orders/qclcd/ . I transform it and store into cassandra. 

# Cassandra Table Schema
The create sql is in createdb.sql file 

# Where is my code 
I write the code in spark-shell. The deatil code is in analysis.scala file.
In the operation.log is my sample log about how I run my code. You can get some idea from the log 


# Conclusion
After some insight, I found that the temperature increase slowly year by year. 

I found that the trend is very hard to find and it is not so obviously in max temperature or avg temperature.
But the avg(min temp) is increasing year by year. 
And the 2013 seems like a very hot year.
