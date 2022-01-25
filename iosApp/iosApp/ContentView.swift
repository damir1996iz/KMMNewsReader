import SwiftUI
import shared

struct ContentView: View {
    @State var str = ""
    
    @ObservedObject var viewModel = NewsViewModel()
    
	var body: some View {
		Text(str)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
