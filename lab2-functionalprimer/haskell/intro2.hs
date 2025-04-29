module Intro2 where
-- test Comment
-- String algebra:
name = "Alice"
-- "if" has a special syntax but otherwise a typed version of Lisp's "if":
name2 = if name /= "" then name else "no name"

pname3 = "Bob"

myprogram = print (1 + m)  -- compiler error: m undefined
m = 1                      -- unless this line is also present

-- sequencing several imperative programs:
prg1 name = do
  putStrLn "You are: " 
  putStrLn name  -- level of indentation is important
  putStrLn ", hello!"

-- the same, but using algebra of imperative programs:
prg1' = sequence_ [print "hello ", print name]

-- one imperative program passing value to another:
prg2 = do
  line <- getLine
  putStrLn ("you typed: " ++ line)

main =
  do
  putStrLn ("Please type in your name")
  myName <- getLine
  putStrLn name -- like print, but only for strings
  putStrLn pname3 -- Couldn't match type ...; Expected type: String; Actual type: IO ()
  myprogram; prg1 myName; prg2 -- sequencing, like 3 lines


-- generic string conversion, using a Java-friendly name:
toString value = show value

-- string concatenation:
greet1 = "hello " ++ name ++ (toString 123)
-- the same, but with the operator as a function (like Lisp):
greet2 = (++) "hello " name
-- concatenating more than 2 strings:
greet3 = concat ["hello ", name, toString 123]
