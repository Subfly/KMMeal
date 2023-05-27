//
//  RandomMealView.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 22.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct RandomMealView: View {
    
    @StateViewModel
    var randomMealStateMachine = RandomMealStateMachine()
    
    @EnvironmentViewModel
    var likedMealsStateMachine: LikedMealsStateMachine
    
    @State
    var title = ""
    
    @State
    var id = "-1"
    
    var body: some View {
        let state = randomMealStateMachine.uiState
        let likedMealIds = likedMealsStateMachine.likedMealIds
        let isLiked = likedMealIds.contains(id)
        
        ZStack {
            if(state.isLoading) {
                ProgressView()
            } else if (!state.error.isEmpty) {
                OnErrorRetryView(
                    message: state.error,
                    action: {
                        randomMealStateMachine.onEvent(
                            event: RandomMealEvent.ReRoll()
                        )
                    }
                )
            } else {
                if let model = state.data {
                    ScrollView(
                        showsIndicators: false
                    ){
                        VStack(
                            alignment: .leading
                        ) {
                            
                            MealDetailHeaderImage(
                                imageUrl: model.imageUrl
                            )
                            .padding(.vertical)
                            
                            MealDetailHeaderSpecificator(
                                area: model.area,
                                category: model.category,
                                tags: model.tags.joined(separator: ", ")
                            )
                            
                            MealDetailTitle(title: "Ingredients")
                            
                            ForEach(model.ingredients, id: \.imageUrl) { ingredient in
                                IngredientItem(model: ingredient)
                                    .padding(.vertical)
                            }
                            
                            MealDetailTitle(title: "Instructions")
                            
                            ForEach(
                                Array(model.instructions.enumerated()),
                                id: \.offset
                            ) { index, instruction in
                                InstructionItem(
                                    step: index + 1,
                                    instruction: instruction
                                )
                                .padding(.vertical)
                            }
                            
                        }
                        .padding(.horizontal, 18)
                    }
                    .task {
                        title = model.name
                        id = model.id
                    }
                }
            }
        }
        .navigationTitle(title)
        .navigationBarTitleDisplayMode(.large)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Image(systemName: isLiked ? "heart.fill" : "heart")
                    .foregroundColor(.red)
                    .onTapGesture {
                        randomMealStateMachine.onEvent(
                            event: RandomMealEvent.UpdateLikedMealStatus(
                                currentStatus: isLiked ? .liked : .notLiked
                            )
                        )
                    }
                    .padding(.trailing, 4)
            }
            ToolbarItem(placement: .navigationBarTrailing) {
                Image(systemName: "dice")
                    .onTapGesture {
                        randomMealStateMachine.onEvent(
                            event: RandomMealEvent.ReRoll()
                        )
                    }
            }
        }
    }
}

struct RandomMealView_Previews: PreviewProvider {
    static var previews: some View {
        RandomMealView()
    }
}
