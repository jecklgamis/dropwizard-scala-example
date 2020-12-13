package dropwizard.scala.example

import dropwizard.scala.example.test.util.{AppTestSupport, HttpUtil, IntegrationFlatSpec}


class ExampleAppIntSpec extends IntegrationFlatSpec with AppTestSupport {

  "App" should "bootstrap ok" in {
    withAppRunning() { server =>
      val url = s"http://localhost:${server.getLocalPort}/"
      HttpUtil.get(url).getStatus should be(200)
    }
  }
}
















