import org.apache.storm.generated.{AlreadyAliveException, InvalidTopologyException}
import org.apache.storm.{Config, StormSubmitter}
import org.apache.storm.topology.TopologyBuilder

class CustomGroupingBuilder {

  val builder = new TopologyBuilder()

  builder.setSpout("Source-Spout",new CustomGroupingSpout())
  builder.setBolt("Bolt",new CustomGroupingBolt(),4)
    .setNumTasks(16)
    .customGrouping("Source-Spout", new CustomGrouping())

  val config = new Config()
  config.setNumWorkers(1)

  try
    StormSubmitter.submitTopology("custom-grouping", config, builder.createTopology())
  catch {
    case alreadyAliveException: AlreadyAliveException =>
      println(alreadyAliveException)
    case invalidTopologyException: InvalidTopologyException =>
      println(invalidTopologyException)
  }

}

object CustomGroupingBuilder{
  def main(args: Array[String]): Unit = {
    new CustomGroupingBuilder()
  }
}
