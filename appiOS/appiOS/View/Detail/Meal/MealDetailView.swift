//
//  MealDetailView.swift
//  KMMeal
//
//  Created by Ali Taha Dinçer on 21.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct MealDetailView: View {
    
    @StateViewModel
    var mealDetailStateMachine = MealDetailStateMachine()
    
    @EnvironmentViewModel
    var likedMealsStateMachine: LikedMealsStateMachine
    
    let mealId: String
    let mealName: String
    
    var body: some View {
        let state = mealDetailStateMachine.uiState
        let likedMealIds = likedMealsStateMachine.likedMealIds
        let isLiked = likedMealIds.contains(mealId)
        
        ZStack {
            if(state.isLoading) {
                ProgressView()
            } else if (!state.error.isEmpty) {
                OnErrorRetryView(
                    message: state.error,
                    action: {
                        mealDetailStateMachine.onEvent(
                            event: MealDetailEvent.Refresh(
                                mealId: mealId
                            )
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
                }
            }
        }
        .navigationTitle(mealName)
        .navigationBarTitleDisplayMode(.large)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Image(systemName: isLiked ? "heart.fill" : "heart")
                    .foregroundColor(.red)
                    .onTapGesture {
                        mealDetailStateMachine.onEvent(
                            event: MealDetailEvent.UpdateLikedMealStatus(
                                currentStatus: isLiked ? .liked : .notLiked
                            )
                        )
                    }
            }
        }
        .task {
            mealDetailStateMachine.onEvent(
                event: MealDetailEvent.InitWithMealId(
                    mealId: mealId
                )
            )
        }
    }
}

struct MealDetailView_Previews: PreviewProvider {
    static let id = "123"
    static let name = "Beef"
    static var previews: some View {
        MealDetailView(
            mealId: id,
            mealName: name
        )
    }
}
