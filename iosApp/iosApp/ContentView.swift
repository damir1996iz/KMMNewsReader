import SwiftUI
import shared

struct ContentView: View {
    let checker = CheckerLong()
    @State var str = ""
    
	var body: some View {
		Text(str)
            .onAppear(perform: {
                str = String(checker.check())
            })
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
