package library.banner

import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.SpringBootVersion
import org.springframework.boot.ansi.AnsiColor
import org.springframework.boot.ansi.AnsiOutput
import org.springframework.boot.ansi.AnsiStyle
import org.springframework.core.env.Environment
import java.io.PrintStream

class BannerGenerator {
    companion object{
        fun customBanner(mainClass: Class<*>): SpringApplication {
            val application = SpringApplication(mainClass)
            application.setBanner(TextBanner())
            return application
        }
    }

    private class SpringBootBanner : Banner {
        override fun printBanner(environment: Environment, sourceClass: Class<*>?, printStream: PrintStream) {
            for (line in BANNER) {
                printStream.println(line)
            }
            var version = SpringBootVersion.getVersion()
            version = if (version != null) " (v$version)" else ""
            val padding = StringBuilder()
            while (padding.length < STRAP_LINE_SIZE - (version.length + SPRING_BOOT.length)) {
                padding.append(" ")
            }
            printStream.println(
                AnsiOutput.toString(
                    AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding.toString(),
                    AnsiStyle.FAINT, version
                )
            )
            printStream.println()
        }

        companion object {
            private val BANNER = arrayOf(
                "", "  .   ____          _            __ _ _",
                " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\", "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\",
                " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )", "  '  |____| .__|_| |_|_| |_\\__, | / / / /",
                " =========|_|==============|___/=/_/_/_/"
            )
            private const val SPRING_BOOT = " :: Spring Boot :: "
            private const val STRAP_LINE_SIZE = 42
        }
    }
    private class TextBanner : Banner {
        override fun printBanner(environment: Environment, sourceClass: Class<*>, out: PrintStream) {
            try{
                val property = environment.getProperty("spring.banner.text")
                property!!.split("\\n")
                    .forEach { out.println(it) }
            } catch (ex: NullPointerException){
                SpringBootBanner().printBanner(environment, sourceClass, out)
            }

        }
    }
}