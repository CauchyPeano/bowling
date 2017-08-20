package bowling

import grails.core.GrailsApplication
import grails.plugins.GrailsPluginManager
import grails.plugins.PluginManagerAware
import grails.rest.*
import grails.converters.*

class BowlingController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

	static responseFormats = ['json']
	
    def index() {

    }

    def roll() {
        [val1: "rool", val2: "roll"]
    }
}
