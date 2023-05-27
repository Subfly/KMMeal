//
//  MealDetailHeaderImage.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI

struct MealDetailHeaderImage: View {
    
    @State
    var canImageLoaded = true
    
    let imageUrl: String
    
    var body: some View {
        if(canImageLoaded) {
            LazyImage(url: URL(string: imageUrl)) { phase in
                if let image = phase.image {
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                } else if let _ = phase.error {
                    EmptyView()
                        .task {
                            canImageLoaded = false
                        }
                } else {
                    ProgressView()
                }
            }
            .cornerRadius(8)
        } else {
            EmptyView()
        }
    }
}

struct MealDetailHeaderImage_Previews: PreviewProvider {
    static var previews: some View {
        MealDetailHeaderImage(
            imageUrl: ""
        )
    }
}
