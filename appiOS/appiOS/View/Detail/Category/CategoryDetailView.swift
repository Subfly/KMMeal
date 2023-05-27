//
//  CategoryDetailView.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct CategoryDetailView: View {
    
    let categoryName: String
    
    @StateViewModel
    var categoryDetailStateMachine = CategoryDetailStateMachine()
    
    var body: some View {
        
        let state = categoryDetailStateMachine.uiState
        
        ZStack {
            if(state.isLoading) {
                ProgressView()
            } else if (!state.error.isEmpty) {
                OnErrorRetryView(
                    message: state.error,
                    action: {
                        categoryDetailStateMachine.onEvent(
                            event: CategoryDetailEvent.Refresh(
                                categoryName: categoryName
                            )
                        )
                    }
                )
            } else {
                
                let (left, right) = setupData(data: state.data)
                
                ScrollView(
                    showsIndicators: false
                ) {
                    HStack(
                        alignment: .top
                    ) {
                        LazyVStack {
                            ForEach(left, id: \.id) { meal in
                                NavigationLink {
                                    MealDetailView(
                                        mealId: meal.id,
                                        mealName: meal.name
                                    )
                                } label: {
                                    MealItem(model: meal)
                                }
                                .buttonStyle(.plain)
                            }
                        }
                        LazyVStack {
                            ForEach(right, id: \.id) { meal in
                                NavigationLink {
                                    MealDetailView(
                                        mealId: meal.id,
                                        mealName: meal.name
                                    )
                                } label: {
                                    MealItem(model: meal)
                                }
                                .buttonStyle(.plain)
                            }
                        }
                    }
                }
                .navigationTitle(categoryName)
                .navigationBarTitleDisplayMode(.large)
                .padding(.horizontal, 18)
            }
        }
        .task {
            categoryDetailStateMachine.onEvent(
                event: CategoryDetailEvent.InitWithCategoryName(
                    categoryName: categoryName
                )
            )
        }
    }
    
    private func setupData(
        data: [MealModel]
    ) -> ([MealModel], [MealModel]) {
        var left: [MealModel] = []
        var rigth: [MealModel] = []
        
        for idx in (0...(data.count - 1)) {
            if (idx % 2 == 0) {
                left.append(data[idx])
            } else {
                rigth.append(data[idx])
            }
        }
        
        return (left, rigth)
    }
}

struct CategoryDetailView_Previews: PreviewProvider {
    static var previews: some View {
        CategoryDetailView(
            categoryName: "Beef"
        )
    }
}
