import com.mpatric.mp3agic.Mp3File
import com.octobeat.Mp3Manager
import org.scalatest.{FunSuite, BeforeAndAfter}

/**
 * Created by sam on 23/10/14.
 */
class Mp3ManagerTest extends FunSuite with BeforeAndAfter {



  before {

  }


  test("A tagged mp3 should be cleaned") {
    val mp3Fixture = Mp3Manager.loadFile(this.getClass.getResource("/fixture.mp3").getPath)
    Mp3Manager.removeTags(mp3Fixture)

    assert(!mp3Fixture.hasCustomTag)
    assert(!mp3Fixture.hasId3v1Tag)
    assert(!mp3Fixture.hasId3v2Tag)

  }

}
