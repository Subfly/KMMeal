//
//  MealDetailHeaderSpecificator.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct MealDetailHeaderSpecificator: View {
    
    let area: String
    let category: String
    let tags: String
    
    var body: some View {
        VStack {
            MealDetailHeaderSpecificatorItem(
                iconSystemName: "globe.europe.africa",
                specificatorTitle: "Area",
                specifierValue: area
            )
            MealDetailHeaderSpecificatorItem(
                iconSystemName: "circle.hexagongrid",
                specificatorTitle: "Category",
                specifierValue: category
            )
            if(!tags.isEmpty) {
                MealDetailHeaderSpecificatorItem(
                    iconSystemName: "number.circle",
                    specificatorTitle: "Tags",
                    specifierValue: tags
                )
            }
        }
    }
}

struct MealDetailHeaderSpecificator_Previews: PreviewProvider {
    static let area = "Turkey"
    static let category = "Beef"
    static let tags = "Tags"
    static var previews: some View {
        MealDetailHeaderSpecificator(
            area: area,
            category: category,
            tags: tags
        )
    }
}
