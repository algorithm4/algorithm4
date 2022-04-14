import os
from re import findall, sub

with open("/Users/kimnuri/PycharmProjects/ComputerAlgorithm/huffman/data/alice_in_wonderland.txt") as f:
    content = " ".join([l.rstrip() for l in f])

Alphabet = 'abcdefghijklmnopqrstuvwxyz'

texts_freq = [0] * 26

#평문 파일에 대한 알파벳 빈도수 계산
for al in content:
    if al in Alphabet:
        idx = Alphabet.find(al)
        texts_freq[idx] += 1

#알파벳 빈도수 출력
print("알파벳 빈도수")
for i in range(0,26):
    print(Alphabet[i], ":", texts_freq[i])

#텍스트 길이
print('texts 길이 =',len(content))
