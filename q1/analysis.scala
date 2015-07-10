val df = sqlContext.read.format("org.apache.spark.sql.cassandra").options(Map( "table" -> "weather" , "keyspace" -> "lala" )).load
df.registerTempTable("weather")

//show 2015 May per day's max,min,avg temperature's mean, stddev, min, max , avg value 
df.filter(df("time") >= 20150501 and df("time") < 20150531 ).describe().show
//show 2013 May per day's max,min,avg temperature's mean, stddev, min, max , avg value 
df.filter(df("time") >= 20130501 and df("time") < 20130531 ).describe().show

//show 2015 May maxtemp's median value 
sqlContext.sql("select percentile(maxtemp,0.5) as median_maxtemp from weather where time < 20150531 and time > 20150501 ").show()
//show 2013 May maxtemp's median value 
sqlContext.sql("select percentile(maxtemp,0.5)  as median_maxtemp from weather where time < 20130531 and time > 20130501 ").show()

//show 2007 ~ 2015 per day's avg min temperature. Show it day by day 
// first step : avg mintemp group by time and set the column's name to avg 
val avg_mintemp_df = df.groupBy("time").agg(avg("mintemp").alias("avg")) 
// second step : grep the date to tuple key 
// example: (20150502, 76.2 ) dataframe will become (0502 , (20150502 , 76.2)) tuple
val date_avgtemp_tuple =  avg_mintemp_df.select((df("time") % 10000).alias("date"),$"time",$"avg").map(r => ( r(0) , (r(1),r(2))))
// final step : group the same date tuple together. At that way, we can see 20070502, 20080502, 20090502, 20100502 ..20150502 together. And get more insight 
val result = date_avgtemp_tuple.groupByKey().values.map(_.toList.sortWith(  _._1.asInstanceOf[Int] <  _._1.asInstanceOf[Int])).collect
result.foreach(println)
