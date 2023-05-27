//
//  CategoryItem.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import NukeUI
import shared

struct CategoryItem: View {
    
    let category: CategoryModel
    
    @State
    var isExpanded = false
    
    var body: some View {
        VStack {
            HStack {
                HStack {
                    if let imageUrl = category.imageUrl {
                        LazyImage(url: URL(string: imageUrl)) { phase in
                            if let image = phase.image {
                                image
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                            } else if phase.error != nil {
                                Image(systemName: "photo")
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                            } else {
                                ProgressView()
                            }
                        }
                        .frame(
                            width: 52,
                            height: 52,
                            alignment: .center
                        )
                        .clipShape(Circle())
                        .padding(.trailing, 8)
                    }
                    Text(category.name)
                }
                Spacer()
                Image(systemName: "chevron.down")
                    .rotationEffect(
                        Angle(
                            degrees: self.isExpanded ? -180 : 0
                        )
                    )
                    .onTapGesture {
                        withAnimation(.easeIn) {
                            self.isExpanded.toggle()
                        }
                    }
            }
            
            if(self.isExpanded) {
                Text(category.description_)
            }
        }
        .padding()
        .background(
            Color.gray.opacity(0.25)
        )
        .cornerRadius(8)
    }
}

struct CategoryItem_Previews: PreviewProvider {
    static let category = CategoryModel(
        id: "123",
        name: "Beef",
        description: "Beef is a beef",
        imageUrl: ""
    )
    static var previews: some View {
        CategoryItem(
            category: category
        )
    }
}
