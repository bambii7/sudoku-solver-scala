package com.cvofhope.sudokusolverscala

import cats.effect.Effect
import io.circe.Json
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class HelloWorldService[F[_]: Effect] extends Http4sDsl[F] {

  val service: HttpService[F] = {
    HttpService[F] {
      case GET -> Root / "hello" / name =>
        Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))

      case GET -> Root / "ping" =>
        Ok()

      case GET -> Root / "same-row" / i / j =>
        val sameRow = Sudoku.isSameRow(i.toInt, j.toInt)
        Ok(s"Same row: ${i} ${j} ${sameRow}")

      case GET -> Root / "same-column" / i / j =>
        val sameColumn = Sudoku.isSameColumn(i.toInt, j.toInt)
        Ok(s"Same column: ${i} ${j} ${sameColumn}")

      case GET -> Root / "same-block" / i / j =>
        val sameBlock = Sudoku.isSameBlock(i.toInt, j.toInt)
        Ok(s"Same block: ${i} ${j} ${sameBlock}")

      case GET -> Root / "solve" / puzzle =>
        val solution = Sudoku.solve(puzzle)
        Ok(s"${solution}")
    }
  }
}
