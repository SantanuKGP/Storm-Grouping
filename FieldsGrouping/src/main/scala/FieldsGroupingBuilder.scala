import org.apache.storm.{Config, StormSubmitter}
import org.apache.storm.generated.{AlreadyAliveException, InvalidTopologyException}
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.tuple.Fields

class FieldsGroupingBuilder {

  val builder = new TopologyBuilder()
  builder.setSpout("first-spout",new FieldsGroupingSpout("first",200), 1)
  builder.setSpout("second-spout",new FieldsGroupingSpout("second",1000), 1)
  builder.setBolt("bolt",new FieldsGroupingBolt(),2)
    .setNumTasks(2)
    .fieldsGrouping("first-spout", new Fields("first"))
    .allGrouping("second-spout")

  val config = new Config()
  config.setNumWorkers(1)

  try
    StormSubmitter.submitTopology("FieldsGrouping-topology", config, builder.createTopology())
  catch {
    case alreadyAliveException: AlreadyAliveException =>
      println(alreadyAliveException)
    case invalidTopologyException: InvalidTopologyException =>
      println(invalidTopologyException)
  }

}
object FieldsGroupingBuilder{
  def main(args: Array[String]): Unit = {
    new FieldsGroupingBuilder()
  }
}