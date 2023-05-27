//
//  LikedMealsView.swift
//  appiOS
//
//  Created by Ali Taha Dinçer on 20.05.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import KMMViewModelSwiftUI
import shared

struct LikedMealsView: View {
    
    @EnvironmentViewModel
    var likedMealsStateMachine: LikedMealsStateMachine
    
    @State
    var showDeleteAlert = false
    
    var body: some View {
        let likedMeals = Array(likedMealsStateMachine.likedMeals)
        
        NavigationView {
            ZStack {
                if(likedMeals.isEmpty) {
                    VStack {
                        Image(systemName: "heart")
                            .resizable()
                            .scaledToFill()
                            .frame(
                                width: 100,
                                height: 100
                            )
                            .padding()
                        Text("It seems you have not liked any meals yet!")
                            .font(.title2)
                            .fontWeight(.medium)
                            .multilineTextAlignment(.center)
                            .padding()
                        Text("Start by pressing the heart icon on the meal detail screen.")
                            .italic()
                            .multilineTextAlignment(.center)
                            .padding(.horizontal)
                    }
                    .opacity(0.5)
                } else {
                    let (left, right) = setupData(data: likedMeals)
                    
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
                }
            }
            .navigationTitle("Liked Meals")
            .navigationBarTitleDisplayMode(.large)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Image(systemName: "trash")
                        .foregroundColor(.red)
                        .onTapGesture {
                            showDeleteAlert = true
                        }
                        .padding(.trailing, 4)
                }
            }
            .padding(.horizontal, 18)
        }
        .alert(isPresented: $showDeleteAlert) {
            Alert(
                title: Text("Delete All"),
                message: Text("Are you sure you want to delete all liked meals? This process can not be undone!"),
                primaryButton: .destructive(
                    Text("I'm sure"),
                    action: {
                        likedMealsStateMachine.onEvent(
                            event: LikedMealsEvent.DeleteAll()
                        )
                        showDeleteAlert = false
                    }
                ),
                secondaryButton: .cancel(
                    Text("Cancel"),
                    action: {
                        showDeleteAlert = false
                    }
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

struct LikedMealsView_Previews: PreviewProvider {
    static var previews: some View {
        LikedMealsView()
    }
}
