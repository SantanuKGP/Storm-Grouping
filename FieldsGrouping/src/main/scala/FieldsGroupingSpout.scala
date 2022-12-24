import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.{Fields, Values}

import java.util

class FieldsGroupingSpout(field: String,sleepTime: Int) extends BaseRichSpout {

  private var outputCollector : SpoutOutputCollector = _
  private var number = 0
  override def open(conf: util.Map[String, Object],
                    context: TopologyContext,
                    collector: SpoutOutputCollector): Unit = {

    this.outputCollector = collector
  }

  override def nextTuple(): Unit = {
    number += 1
    Thread.sleep(sleepTime)
    outputCollector.emit(new Values(number))
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields(s"$field"))
  }

}
