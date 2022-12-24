import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.{Fields, Values}

import java.util
import scala.util.Random
@SerialVersionUID(1L)
class FirstTopologySpout extends BaseRichSpout{
  private var outputCollector : SpoutOutputCollector = _
  private var number = 0
  private val keys = keyProducer("key", 100)
  private val users = keyProducer("user", 3)

  override def open(conf: util.Map[String, Object],
                    context: TopologyContext,
                    collector: SpoutOutputCollector): Unit = {

    this.outputCollector = collector
  }

  override def nextTuple(): Unit = {
    number += 1
    Thread.sleep(200)

    try {
      val random_num = new Random().nextInt(keys.length)
      val keyVal = keys(random_num)
      val username = users(new Random().nextInt(users.length))
      outputCollector.emit(new Values(number, keyVal,username))
    }
    catch{
      case _: IndexOutOfBoundsException =>  outputCollector.emit(new Values(number, "No Key"))
      case _: Throwable => outputCollector.emit(new Values(number, "Error Key"))
    }

  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("first","key","user"))
  }
  private def keyProducer(str: String, n:Int):List[String] = {
    ( 1 to n).toList.map(str+_)
  }
}
