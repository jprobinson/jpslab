import java.text.SimpleDateFormat

class LabTagLib {
	
	/*
	 * This tag will replace any photo tags with actual <img> tags
	 * and it will replace any new line chars with <br/> so the line 
	 * break will actuallty show in html 
	 */
	def textAndImages = { attrs ->
		def text = attrs['value']
		text = replaceTextandImages(text,"Left")

		text = replaceTextandImages(text,"Right")

		text = replaceTextandImages(text,"")
		text = text.replaceAll(/\n/,"<br/>")
		
		out << text
	}
	 
	private String replaceTextandImages(text,direction){
		 def upDirection = direction.toUpperCase()
		 def photoRegex = /<PHOTO$upDirection>.+<PHOTO$upDirection>/
 
		text.eachMatch(photoRegex){ match ->
			def photoTag = match
			def imageName = photoTag.replaceAll(/<PHOTO$upDirection>/,'')
			def photo = Photo.findByName(imageName)
			def photoSrc = ""
			if(!photo){
				photo = Photo.findByUrl(imageName)
				photoSrc = photo.url
			}
			else{
				photoSrc = createLinkTo(dir:'images', file:imageName)
			}
		
			if(photo!=null){	
				def imageTag = 
					"""<div class="imageHolder${direction}">
						<img class="labImage${direction}" src="${photoSrc}"  />
						<center>${photo.getCaption()}</center>
					</div>"""
					
					text = text.replaceAll(photoTag,imageTag)
					
			}
		}
		 text 
	 }
	 
}
