import SwiftUI
import shared

enum Categories: Identifiable {
        case category

        var id: Int {
            self.hashValue
        }
    }

struct ContentView: View {
    @StateObject var newsViewModel = NewsViewModelWrapper()
    
    @State var activeSheet:Categories?
    
	var body: some View {
        NavigationView
        {
            ZStack
            {
                if(newsViewModel.news is NewsLoadingState.Success)
                {
                    NewsView(news:
                                (newsViewModel.news as! NewsLoadingState.Success).result)
                }
                else if(newsViewModel.news is NewsLoadingState.Loading)
                {
                    ProgressView()
                        .padding()
                }
                else
                {
                    Text("Nothing found in cache.\nSelect another Category, or check internet connection")
                        .padding()
                }
            }
            .navigationBarTitleDisplayMode(.inline)
            .toolbar(content: {
                ToolbarItem(placement: .principal) {
                    VStack(spacing:0) {
                                    Text(
                                        "KMM News Reader"
                                    ).font(.headline)
                                    Button(action: {
                                        activeSheet = .category
                                    }, label: {
                                        Text(newsViewModel.newsType.desc)
                                            .font(.subheadline)
                                    })
                                }
                            }})
        }
        .navigationViewStyle(StackNavigationViewStyle())
        .sheet(item: $activeSheet, onDismiss: {activeSheet = nil},content: {
            item in
            if (item == .category)
            {
                CategorySelectView(onItemClicked: {
                    value in
                    newsViewModel.loadNews(type: value)
                    activeSheet = nil
                })
            }
        })
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

struct CategorySelectView:View {
    var types = NewsTypes.values()
    var onItemClicked:(NewsTypes)->Void
    
    var body: some View
    {
        VStack
        {
            ForEach(0..<types.size, id:\.self)
            {
                index in
                Button(types.get(index: index)?.desc ?? "", action: {
                    onItemClicked(types.get(index: index)!)
                })
            }
        }
    }
}
