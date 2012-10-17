grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.war.resources = { stagingDir, args ->
    copy(file: "${userHome}/.grails/${appName}-mandrill-config.groovy",
         toDir: "${stagingDir}/WEB-INF/classes")
}
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		mavenRepo "http://maven.springframework.org/milestone/"
		mavenRepo "http://snapshots.repository.codehaus.org/"
		mavenRepo "https://oss.sonatype.org/content/repositories/releases/"


    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
		compile 'com.google.zxing:core:2.0'
		compile 'com.google.zxing:javase:2.0'
		compile 'org.ccil.cowan.tagsoup:tagsoup:1.2.1'
		runtime 'org.springframework.integration:spring-integration-amqp:2.1.3.RELEASE'

//		compile('xerces:xercesImpl:2.10.0')

        // runtime 'mysql:mysql-connector-java:5.1.18'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.6"
		compile ":cloud-foundry:1.2.3"
		compile ":spring-mobile:0.4"
		compile ":lesscss-resources:1.3.0.3"

		runtime ":events-si:1.0.M3"
		compile ":events-push:1.0.M3"
		compile ":mandrill:0.1"
		//compile ":twitter-bootstrap:2.1.1"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"

        //runtime ":database-migration:1.1"
        
     //   compile ':cache:1.0.0.RC1'
    }

}
