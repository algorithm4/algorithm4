import os
from re import findall, sub

print('\n현재 경로 :', os.getcwd())

with open("/Users/kimnuri/PycharmProjects/ComputerAlgorithm/huffman/data/alice_in_wonderland.txt") as f:
    content = " ".join([l.rstrip() for l in f])

def clean_text(content):
    texts_re = content.lower() #소문자로 변경
    texts_re2 = sub('[0-9]', '', texts_re) #숫자 제거
    texts_re3 = sub('[\'`,.?!;:"\-\[\]]', '', texts_re2) #문장부호 제거
    texts_re4 = sub('[@#$%^&*()\_\+\=]', '', texts_re3) #특수문자 제거
    texts_re5 = sub('[가-힣]','',texts_re4) #한글 제거
    texts_re6 = texts_re5.replace(' ','') #white space 제거
    return texts_re6

print(clean_text(content))

texts2 = open('/Users/kimnuri/PycharmProjects/ComputerAlgorithm/huffman/data/alice_in_wonderland.txt',mode='w',encoding='UTF8')
texts2.write(clean_text(content))
texts2.close()
