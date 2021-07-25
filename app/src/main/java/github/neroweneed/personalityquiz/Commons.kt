package github.neroweneed.personalityquiz

import github.neroweneed.personalityquiz.data.Nature
import github.neroweneed.personalityquiz.data.Response
import github.neroweneed.personalityquiz.data.ResponsePoint

fun Collection<ResponsePoint>.calculatePersonality() = this.groupBy { it.nature }
    .map { a -> a.key to a.value.sumOf { b -> b.points } }
    .reduce { acc, pair -> if (acc.second > pair.second) acc else pair }.first
