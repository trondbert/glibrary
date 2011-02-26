package libraryapp

class DateTagLib {
	def thisYear = {
		out << Calendar.getInstance().get(Calendar.YEAR)
	}
}
