//
//  Destination.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

enum Destination: Hashable {
    case categoryDetail(categoryName: String)
    case mealDetaul(mealId: String)
}
