import grails.converters.*

class BoxController {

    def edit = { 
    		render(view:'edit', model:[box:Box.findByBoxName(params.name)])
    }
    
    def saveBox = {
    	def boxInstance = Box.findById(params.id)
    	boxInstance.setText(params.text)
    	boxInstance.setFontColor(params.fontColor)
    	boxInstance.setFontRedNum(params.fontRedNum)
    	boxInstance.setFontGreenNum(params.fontGreenNum)
    	boxInstance.setFontBlueNum(params.fontBlueNum)
    	boxInstance.setBackColor(params.backColor)
    	boxInstance.setBackRedNum(paqrams.backRedNum)
    	boxInstance.setBackGreenNum(params.backGreenNum)
    	boxInstance.setBackBlueNum(params.backBlueNum)
    	boxInstance.save()
    	render boxInstance as JSON
    }
}
