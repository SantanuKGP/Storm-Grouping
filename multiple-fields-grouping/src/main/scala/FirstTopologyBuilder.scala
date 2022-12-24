import org.apache.storm.{Config, StormSubmitter}
import org.apache.storm.generated.{AlreadyAliveException, InvalidTopologyException}
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.tuple.Fields

class FirstTopologyBuilder {

  // Setting up the Topology
  val builder = new TopologyBuilder()

  // Setting up the Spout
  builder.setSpout("first-spout",new FirstTopologySpout(), 1)

  // Setting up the bolt
  builder.setBolt("bolt",new FirstTopologyBolt(),3)
    .setNumTasks(3)
    .fieldsGrouping("first-spout", new Fields("key","user"))

  val config = new Config()
  config.setNumWorkers(1)

  try
    StormSubmitter.submitTopology("field-grouping", config, builder.createTopology())
  catch {
    case alreadyAliveException: AlreadyAliveException =>
      println(alreadyAliveException)
    case invalidTopologyException: InvalidTopologyException =>
      println(invalidTopologyException)
  }

}

object FirstTopologyBuilder{
  def main(args: Array[String]): Unit = {
    new FirstTopologyBuilder()
  }
}