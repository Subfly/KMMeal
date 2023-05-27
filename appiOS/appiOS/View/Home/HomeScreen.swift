//
//  HomeScreen.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct HomeScreen: View {
    @State
    var tabSelection = HomeTabType.categories
    
    var body: some View {
        TabView(selection: $tabSelection) {
            CategoriesView()
                .tabItem {
                    Image(systemName: "list.bullet")
                    Text("Categories")
                }
                .tag(HomeTabType.categories)
            SearchView()
                .tabItem {
                    Image(systemName: "magnifyingglass")
                    Text("Search")
                }
                .tag(HomeTabType.search)
            LikedMealsView()
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text("Liked Meals")
                }
                .tag(HomeTabType.liked)
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
