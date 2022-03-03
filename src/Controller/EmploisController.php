<?php

namespace App\Controller;


use App\Entity\Emplois;
use App\Form\EmploisType;
use App\Repository\EmploisRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;

/**
 * @Route("/emplois")
 */
class EmploisController extends AbstractController
{
    
    /**
     * @Route("/", name="emplois_index", methods={"GET"})
     */
    public function index(EmploisRepository $emploisRepository): Response
    {
        return $this->render('emplois/index.html.twig', [
            'emplois' => $emploisRepository->findAll(),
        ]);
    }
    
  /**
     * @Route("/Allemp", name="Allemp")
     */
    public function Allemp(NormalizerInterface $Normalizer )
    {
    //Nous utilisons la Repository pour récupérer les objets que nous avons dans la base de données
    $repository =$this->getDoctrine()->getRepository(Emplois::class);
    $emplois=$repository->FindAll();
    //Nous utilisons la fonction normalize qui transforme en format JSON nos donnée qui sont
    //en tableau d'objet Students
    $jsonContent=$Normalizer->normalize($emplois,'json',['groups'=>'post:read']);
    
    
    
    return new Response(json_encode($jsonContent));
    dump($jsonContent);
    die;}
     
    /**
     * @Route("/new", name="emplois_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $emploi = new Emplois();
        $form = $this->createForm(EmploisType::class, $emploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $user = $form->get('User')->getData();
            $emploi->setnom($user->getnom());
            $emploi->setprenom($user->getprenom());
            $emploi->setCIN($user->getcin());
            $entityManager->persist($emploi);
            $entityManager->flush();

            return $this->redirectToRoute('emplois_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('emplois/new.html.twig', [
            'emploi' => $emploi,
            'form' => $form->createView(),
        ]);
    }
 
    /**
     * @Route("/{id}", name="emplois_show", methods={"GET"})
     */
    public function show(Emplois $emploi): Response
    {
        return $this->render('emplois/show.html.twig', [
            'emploi' => $emploi,
        ]);
    }
     /**
     * @Route("/searchemp/{searchString}", name="searchemp")
     */
    public function searchemp($searchString,SerializerInterface $serializer)
    {
        
       
        $repository =$this->getDoctrine()->getRepository(Emplois::class);
     

        $emplois = $repository->findByExampleField($searchString);
        
    
        $data = $serializer->normalize($emplois,'json',['groups'=>'post:read']);
        return new JsonResponse($data);
       // $data=$serializer->normalize("");
       // return new Response(json_encode($data));
    }
     
    /**
     * @Route("/{id}/edit", name="emplois_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Emplois $emploi, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EmploisType::class, $emploi);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $user = $form->get('User')->getData();
            $emploi->setnom($user->getnom());
            $emploi->setprenom($user->getprenom());
            $emploi->setCIN($user->getcin());
            $entityManager->flush();

            return $this->redirectToRoute('emplois_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('emplois/edit.html.twig', [
            'emploi' => $emploi,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="emplois_delete", methods={"POST"})
     */
    public function delete(Request $request, Emplois $emploi, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$emploi->getId(), $request->request->get('_token'))) {
            $entityManager->remove($emploi);
            $entityManager->flush();
        }

        return $this->redirectToRoute('emplois_index', [], Response::HTTP_SEE_OTHER);
    }

}
