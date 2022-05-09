package utils

import java.util.TimerTask

object Time {
  implicit def timeFunc(f: () => Unit): TimerTask = {
    new TimerTask {
      def run(): Unit = f()
    }
  }
}