//
//  KMMealNavigationView.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct KMMealNavigationView: View {
    @State
    var path = NavigationPath()
    
    @StateViewModel
    var likedMealsStateMachine = LikedMealsStateMachine()
    
    
    var body: some View {
        NavigationStack(path: $path) {
            HomeScreen()
        }
        .environmentViewModel(likedMealsStateMachine)
    }
}

struct KMMealNavigationView_Previews: PreviewProvider {
    static var previews: some View {
        KMMealNavigationView()
    }
}
