import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

object MainApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Export Aggregator")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val year = 2018  // Example year
    val commodity = "MEAT AND EDIBLE MEAT OFFAL."  // Example commodity
    val inputFilePath = "/Users/manishkumartyagi/Downloads/Categorize/src/main/scala/2018-2010_export.csv"

    println(s"Attempting to read input file from: $inputFilePath")

    val inputData: DataFrame = spark.read.option("header", "true").csv(inputFilePath)
    val aggregatedData = ExportAggregator.aggregateData(inputData, year, commodity)
    val categorizedData = aggregatedData.withColumn("category", ExportAggregator.categorizeUDF($"total_value"))

    val sanitizedCommodity = commodity.replaceAll("[/\\\\:*?\"<>|]", "_")
    categorizedData
      .write
      .mode("overwrite")
      .partitionBy("category")

      .csv(s"/Users/manishkumartyagi/Downloads/Categorize/src/output/$year/$sanitizedCommodity")

    spark.stop()
  }
}
